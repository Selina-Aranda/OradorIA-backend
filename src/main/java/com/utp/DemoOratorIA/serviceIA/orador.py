import cv2
import mediapipe as mp
import sounddevice as sd
from scipy.io.wavfile import write
import whisper
import threading
import time
import numpy as np
import re
import os
import librosa
import requests  # <-- NUEVO: Para llamar a Ollama
import json

# =========================
# FFmpeg FIX
# =========================
os.environ["PATH"] += os.pathsep + r"C:\Users\USUARIO\AppData\Local\Microsoft\WinGet\Packages\Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe\ffmpeg-8.1.1-full_build\bin"

# =========================
# OLLAMA (IA COACH LOCAL) - NUEVO
# =========================
def analisis_ia(texto, ppm, muletillas, miradas, pausas):
    """
    Usa Ollama local en lugar de OpenAI (GRATIS)
    """
    prompt = f"""
Eres un coach profesional de oratoria.

Analiza este discurso:

TEXTO:
{texto}

VELOCIDAD:
{ppm} ppm

MULETILLAS DETECTADAS:
{muletillas}

PAUSAS/HESITACIONES (silencios largos):
{pausas}

MIRADAS DESVIADAS:
{miradas}

Da:
1. evaluación general
2. errores principales
3. mejoras
4. puntuación sobre 100

Formato de respuesta: Usa números (1., 2., 3., 4.) y sé específico.
"""

    # Configuración para Ollama
    url = "http://localhost:11434/api/generate"
    
    payload = {
        "model": "llama3.2",  # El modelo que descargaste
        "prompt": prompt,
        "stream": False,
        "temperature": 0.7,
        "max_tokens": 1000
    }
    
    try:
        print("🧠 Analizando con Ollama...")
        response = requests.post(url, json=payload)
        response.raise_for_status()
        result = response.json()
        return result["response"]
    except requests.exceptions.ConnectionError:
        return """
❌ ERROR: Ollama no está corriendo.

SOLUCIÓN:
1. Abre Ollama desde el menú de inicio
2. Espera a que aparezca el icono en la bandeja del sistema
3. Vuelve a ejecutar este script
        """
    except Exception as e:
        return f"❌ Error con Ollama: {str(e)}"

# =========================
# CONFIG
# =========================
DURACION = 30
FS = 44100

stop = False
miradas_eventos = 0

# =========================
# AUDIO
# =========================
def grabar_audio():
    global stop

    print("🎤 Grabando audio...")

    audio = sd.rec(
        int(DURACION * FS),
        samplerate=FS,
        channels=1,
        dtype='float32'
    )

    sd.wait()

    audio_int16 = np.int16(audio * 32767)
    write("audio.wav", FS, audio_int16)

    print("✅ Audio guardado")


# =========================
# VIDEO (FACE TRACKING)
# =========================
def analizar_video():
    global stop, miradas_eventos

    mp_face = mp.solutions.face_mesh
    face_mesh = mp_face.FaceMesh(refine_landmarks=True)

    cam = cv2.VideoCapture(0)

    inicio = time.time()
    ultima_alerta = 0

    while not stop:

        ret, frame = cam.read()
        if not ret:
            break

        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        results = face_mesh.process(rgb)

        if results.multi_face_landmarks:

            for face in results.multi_face_landmarks:

                nariz = face.landmark[1]
                ojo_l = face.landmark[468]
                ojo_r = face.landmark[473]

                centro_ojos = (ojo_l.x + ojo_r.x) / 2
                desviacion = nariz.x - centro_ojos

                # conteo real (con cooldown)
                if abs(desviacion) > 0.05:
                    if time.time() - ultima_alerta > 1:
                        miradas_eventos += 1
                        ultima_alerta = time.time()

        cv2.imshow("ORADOR IA PRO MAX", frame)

        if time.time() - inicio > DURACION:
            stop = True
            break

        if cv2.waitKey(1) == 27:
            stop = True
            break

    cam.release()
    cv2.destroyAllWindows()


# =========================
# TRANSCRIPCIÓN
# =========================
def transcribir(model):
    print("🧠 Transcribiendo...")

    result = model.transcribe(
        "audio.wav",
        language="es",
        condition_on_previous_text=False,
        temperature=0,
        verbose=False
    )
    return result["text"].lower()


# =========================
# NORMALIZACIÓN DE TEXTO
# =========================
def normalizar_texto(texto):
    texto = re.sub(r"[^\wáéíóúñü\s]", " ", texto.lower())
    texto = re.sub(r"\s+", " ", texto).strip()
    return texto


# =========================
# MULETILLAS (TEXTO) - MEJORADO
# =========================
def detectar_muletillas_texto(texto):
    texto = normalizar_texto(texto)

    patrones = {
        "eh":          r"\be+h+\b",
        "em":          r"\bem+\b",
        "mmm":         r"\bm{2,}\b",
        "este":        r"\beste+\b",
        "esto":        r"\besto+\b",
        "bueno":       r"\bbu+e+no+\b",
        "o sea":       r"\bo\s+se+a+\b",
        "como":        r"\bcomo\b",
        "digamos":     r"\bdigamos\b",
        "entonces":    r"\bentonces\b",
        "claro":       r"\bclaro\b",
        "verdad":      r"\bverdad\b",
        "obviamente":  r"\bobviamente\b",
        "tipo":        r"\btipo\b",
        "ya tu sabes": r"\bya\s+tu\s+sabes\b",
        "no se":       r"\bno\s+se\b",
        "literal":     r"\bliteral(?:mente)?\b",
        "ajam":        r"\baj+am+\b",
    }

    conteos = {}
    for nombre, patron in patrones.items():
        matches = re.findall(patron, texto)
        if matches:
            conteos[nombre] = len(matches)

    return conteos


# =========================
# VELOCIDAD
# =========================
def palabras_por_minuto(texto):
    palabras = len(texto.split())
    return round(palabras / (DURACION / 60))


# =========================
# PAUSAS / HESITACIONES (AUDIO)
# =========================
def detectar_pausas(path="audio.wav", umbral_silencio=30, min_duracion=0.5):
    """
    Detecta silencios largos dentro del habla (no al inicio/final)
    que suelen indicar dudas o muletillas no verbales.
    umbral_silencio: dB bajo el pico para considerar silencio
    min_duracion: segundos mínimos para contar como pausa relevante
    """
    y, sr = librosa.load(path, sr=None)

    intervalos_sonido = librosa.effects.split(
        y, top_db=umbral_silencio
    )

    pausas = []
    for i in range(len(intervalos_sonido) - 1):
        fin_actual = intervalos_sonido[i][1]
        inicio_siguiente = intervalos_sonido[i + 1][0]

        duracion_pausa = (inicio_siguiente - fin_actual) / sr

        if duracion_pausa >= min_duracion:
            pausas.append(round(duracion_pausa, 2))

    return {
        "cantidad_pausas_largas": len(pausas),
        "duracion_total_pausas_seg": round(sum(pausas), 2),
        "pausa_mas_larga_seg": round(max(pausas), 2) if pausas else 0
    }



def ejecutar_analisis():
    
    model = whisper.load_model("base")

    hilo_audio = threading.Thread(target=grabar_audio)
    hilo_video = threading.Thread(target=analizar_video)

    hilo_audio.start()
    hilo_video.start()

    hilo_audio.join()
    hilo_video.join()

    texto = transcribir(model)

    muletillas_texto = detectar_muletillas_texto(texto)
    ppm = palabras_por_minuto(texto)
    pausas = detectar_pausas("audio.wav")

    resultado = analisis_ia(
        texto,
        ppm,
        muletillas_texto,
        miradas_eventos,
        pausas
    )

    return {
        "texto": texto,
        "ppm": ppm,
        "muletillas": muletillas_texto,
        "miradas": miradas_eventos,
        "pausas": pausas,
        "analisis": resultado
    }

# =========================
# MAIN
# =========================
if __name__ == "__main__":

    print("================================")
    print("      ORADOR IA PRO MAX")
    print("================================")
    print("🎯 Usando Ollama con llama3.2 (GRATIS)")
    print("")

    # Verificar que Ollama está corriendo
    try:
        requests.get("http://localhost:11434/api/tags", timeout=2)
        print("✅ Ollama está corriendo")
    except:
        print("⚠️ Ollama no está corriendo. Ábrelo desde el menú de inicio.")
        print("   El script continuará pero el análisis IA fallará.")
        print("")

    model = whisper.load_model("base")

    hilo_audio = threading.Thread(target=grabar_audio)
    hilo_video = threading.Thread(target=analizar_video)

    hilo_audio.start()
    hilo_video.start()

    hilo_audio.join()
    hilo_video.join()

    # =========================
    # ANALISIS
    # =========================
    texto = transcribir(model)
    muletillas_texto = detectar_muletillas_texto(texto)
    ppm = palabras_por_minuto(texto)
    pausas = detectar_pausas("audio.wav")

    total_muletillas = sum(muletillas_texto.values())

    # =========================
    # REPORTE BASE
    # =========================
    print("\n========================")
    print("REPORTE BASE")
    print("========================")

    print("\n📜 Texto:")
    print(texto)

    print("\n🎤 Velocidad (PPM):", ppm)

    print("\n⚠️ Muletillas detectadas:")
    if muletillas_texto:
        for k, v in muletillas_texto.items():
            print(f"{k}: {v}")
        print(f"Total: {total_muletillas}")
    else:
        print("Ninguna detectada")

    print("\n⏸️ Pausas/hesitaciones:")
    print(pausas)

    print(f"\n👀 Miradas desviadas: {miradas_eventos}")

    # =========================
    # IA COACH CON OLLAMA
    # =========================
    print("\n" + "="*40)
    print("🧠 ANÁLISIS DEL COACH IA (Ollama)")
    print("="*40 + "\n")

    resultado = analisis_ia(
        texto,
        ppm,
        muletillas_texto,
        miradas_eventos,
        pausas
    )

    print(resultado)

    print("\n" + "="*40)
    print("✅ Fin del análisis")