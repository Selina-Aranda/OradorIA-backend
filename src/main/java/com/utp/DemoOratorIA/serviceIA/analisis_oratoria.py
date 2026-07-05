# analisis_oratoria.py
# Módulo que coordina todo el flujo de análisis

from grabador import iniciar_grabacion, obtener_miradas, obtener_duracion, esta_grabando
from whisper_ai import transcribir
from utils import detectar_muletillas_texto, palabras_por_minuto, detectar_pausas
from ia import analisis_ia
import time

def main(configuracion=None):
    configuracion = configuracion or {}
    DURACION = int(configuracion.get("duracion_segundos", 30) or 30)
    
    # 1. Iniciar grabación
    iniciar_grabacion(DURACION)
    
    # 2. Esperar a que termine
    while esta_grabando():
        time.sleep(0.1)
    
    # 3. Obtener datos de la grabación
    miradas = obtener_miradas()  # Esto es un número (int)
    duracion = obtener_duracion()
    
    # 4. Transcribir audio
    texto = transcribir("audio.wav")
    
    # 5. Análisis de texto
    muletillas = detectar_muletillas_texto(texto)
    ppm = palabras_por_minuto(texto, duracion)
    pausas = detectar_pausas("audio.wav")  # Esto es un diccionario
    
    # 6. Análisis con IA - PASAR PARÁMETROS EN ORDEN CORRECTO
    analisis = analisis_ia(
        texto=texto,
        ppm=ppm,
        muletillas=muletillas,
        pausas=pausas,
        miradas=miradas
    )
    
    # 7. Construir resultado para Spring Boot
    resultado = {
        "texto": texto,
        "ppm": ppm,
        "muletillas": muletillas,
        "total_muletillas": sum(muletillas.values()),
        "miradas": miradas,
        "pausas": pausas,
        "duracion": duracion,
        "fluidez": analisis.get("fluidez", 0),
        "claridad": analisis.get("claridad", 0),
        "volumen": analisis.get("volumen", 0),
        "velocidad": analisis.get("velocidad", 0),
        "postura": analisis.get("postura", 0),
        "contacto_visual": analisis.get("contacto_visual", 0),
        "confianza": analisis.get("confianza", 0),
        "expresion_facial": analisis.get("expresion_facial", 0),
        "muletillas_detectadas": analisis.get("muletillas_detectadas", 0),
        "pausas_incomodas": analisis.get("pausas_incomodas", 0),
        "miradas_desviadas": analisis.get("miradas_desviadas", 0),
        "puntuacion_general": analisis.get("puntuacion_general", 0),
        "errores_detectados": analisis.get("errores_detectados", ""),
        "recomendaciones": analisis.get("recomendaciones", "")
    }
    
    return resultado