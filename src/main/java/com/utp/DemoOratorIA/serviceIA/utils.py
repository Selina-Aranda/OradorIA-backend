# utils.py
# Módulo de funciones auxiliares reutilizables

import re
import librosa

def normalizar_texto(texto):
    texto = re.sub(r"[^\wáéíóúñü\s]", " ", texto.lower())
    texto = re.sub(r"\s+", " ", texto).strip()
    return texto

def detectar_muletillas_texto(texto):
    texto = normalizar_texto(texto)
    patrones = {
        "eh": r"\be+h+\b",
        "em": r"\bem+\b",
        "mmm": r"\bm{2,}\b",
        "este": r"\beste+\b",
        "esto": r"\besto+\b",
        "bueno": r"\bbu+e+no+\b",
        "o sea": r"\bo\s+se+a+\b",
        "como": r"\bcomo\b",
        "digamos": r"\bdigamos\b",
        "entonces": r"\bentonces\b"
    }
    conteos = {}
    for nombre, patron in patrones.items():
        matches = re.findall(patron, texto)
        if matches:
            conteos[nombre] = len(matches)
    return conteos

def palabras_por_minuto(texto, duracion):
    palabras = len(texto.split())
    return round(palabras / (duracion / 60))

def detectar_pausas(audio_path, umbral_silencio=30, min_duracion=0.5):
    y, sr = librosa.load(audio_path, sr=None)
    intervalos_sonido = librosa.effects.split(y, top_db=umbral_silencio)
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