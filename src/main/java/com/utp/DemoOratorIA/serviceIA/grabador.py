# grabador.py
# Módulo exclusivo para captura de audio y video

import cv2
import mediapipe as mp
import sounddevice as sd
from scipy.io.wavfile import write
import threading
import time
import numpy as np
import os

# Variables globales de estado
_stop = False
_miradas_eventos = 0
_grabacion_activa = False
_hilo_audio = None
_hilo_video = None
_duracion_actual = 0

# Configuración FFmpeg (ajusta tu ruta)
os.environ["PATH"] += os.pathsep + r"C:\Users\USUARIO\AppData\Local\Microsoft\WinGet\Packages\Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe\ffmpeg-8.1.1-full_build\bin"

def iniciar_grabacion(duracion=30):
    global _grabacion_activa, _hilo_audio, _hilo_video, _stop, _miradas_eventos, _duracion_actual
    
    if _grabacion_activa:
        print("⚠️ Ya hay una grabación en curso")
        return False
    
    _stop = False
    _miradas_eventos = 0
    _duracion_actual = duracion
    _grabacion_activa = True
    
    print(f"🎥 Iniciando grabación por {duracion} segundos...")
    
    _hilo_audio = threading.Thread(target=grabar_audio, args=(duracion,))
    _hilo_video = threading.Thread(target=analizar_video, args=(duracion,))
    
    _hilo_audio.start()
    _hilo_video.start()
    
    return True

def detener_grabacion():
    global _stop, _grabacion_activa, _hilo_audio, _hilo_video
    
    if not _grabacion_activa:
        print("⚠️ No hay grabación activa para detener")
        return False
    
    print("🛑 Deteniendo grabación...")
    _stop = True
    
    if _hilo_audio and _hilo_audio.is_alive():
        _hilo_audio.join(timeout=2)
    if _hilo_video and _hilo_video.is_alive():
        _hilo_video.join(timeout=2)
    
    _grabacion_activa = False
    print("✅ Grabación detenida")
    return True

def grabar_audio(duracion):
    print("🎤 Grabando audio...")
    fs = 44100
    audio = sd.rec(int(duracion * fs), samplerate=fs, channels=1, dtype='float32')
    sd.wait()
    audio_int16 = np.int16(audio * 32767)
    write("audio.wav", fs, audio_int16)
    print("✅ Audio guardado")

def analizar_video(duracion):
    global _stop, _miradas_eventos
    mp_face = mp.solutions.face_mesh
    face_mesh = mp_face.FaceMesh(refine_landmarks=True)
    cam = cv2.VideoCapture(0)
    inicio = time.time()
    ultima_alerta = 0
    miradas = 0
    
    while not _stop:
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
                if abs(desviacion) > 0.05:
                    if time.time() - ultima_alerta > 1:
                        miradas += 1
                        _miradas_eventos = miradas
                        ultima_alerta = time.time()
        cv2.imshow("ORADOR IA PRO MAX", frame)
        if time.time() - inicio > duracion:
            _stop = True
            break
        if cv2.waitKey(1) == 27:
            _stop = True
            break
    
    cam.release()
    cv2.destroyAllWindows()
    return miradas

def esta_grabando():
    return _grabacion_activa

def obtener_miradas():
    return _miradas_eventos

def obtener_duracion():
    return _duracion_actual