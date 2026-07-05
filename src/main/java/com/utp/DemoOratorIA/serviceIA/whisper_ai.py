# whisper_ai.py
# Módulo exclusivo para transcripción con Whisper

import whisper
import re

modelo = whisper.load_model("base")

def transcribir(audio_path):
    print("🧠 Transcribiendo...")
    result = modelo.transcribe(audio_path, language="es", condition_on_previous_text=False)
    return result["text"].lower()

def normalizar_texto(texto):
    texto = re.sub(r"[^\wáéíóúñü\s]", " ", texto.lower())
    texto = re.sub(r"\s+", " ", texto).strip()
    return texto