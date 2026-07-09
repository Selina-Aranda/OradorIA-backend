# ia.py
# Módulo exclusivo para comunicación con Ollama

import requests
import json
import re


def analisis_ia(texto, ppm, muletillas, pausas, miradas):

    total_muletillas = sum(muletillas.values())

    # Si miradas es un diccionario
    if isinstance(miradas, dict):
        cantidad_miradas = miradas.get("cantidad_miradas_desviadas", 0)
    else:
        cantidad_miradas = miradas

    # Si pausas es un diccionario
    if isinstance(pausas, dict):
        cantidad_pausas = pausas.get("cantidad_pausas_largas", 0)
    else:
        cantidad_pausas = pausas

    prompt = f"""
Eres un coach profesional de oratoria.

Analiza el siguiente discurso utilizando la información proporcionada.

TEXTO:
{texto}

VELOCIDAD:
{ppm} palabras por minuto

MULETILLAS DETECTADAS:
{total_muletillas}

PAUSAS LARGAS:
{cantidad_pausas}

MIRADAS DESVIADAS:
{cantidad_miradas}

Responde ÚNICAMENTE con un JSON válido exactamente con este formato:

{{
    "fluidez": 0,
    "claridad": 0,
    "volumen": 0,
    "velocidad": 0,
    "postura": 0,
    "contacto_visual": 0,
    "confianza": 0,
    "expresion_facial": 0,
    "puntuacion_general": 0,
    "errores_detectados": "",
    "recomendaciones": ""
}}

Las calificaciones deben estar entre 0 y 10.
No escribas explicaciones.
No escribas markdown.
No uses ```json.
Devuelve solamente el JSON.
"""

    url = "http://localhost:11434/api/generate"

    payload = {
        "model": "llama3.2",
        "prompt": prompt,
        "stream": False,
        "temperature": 0.3
    }

    try:

        print("🧠 Analizando con Ollama...")

        response = requests.post(url, json=payload)
        response.raise_for_status()

        respuesta = response.json()["response"]

        json_match = re.search(r"\{.*\}", respuesta, re.DOTALL)

        if not json_match:
            return {
                "error": "No se encontró un JSON válido.",
                "respuesta": respuesta
            }

        resultado = json.loads(json_match.group())

        # Agregar métricas obtenidas por tu sistema
        resultado["muletillas_detectadas"] = total_muletillas
        resultado["pausas_incomodas"] = cantidad_pausas
        resultado["miradas_desviadas"] = cantidad_miradas
        resultado["velocidad_ppm"] = ppm

        return resultado

    except Exception as e:
        return {
            "error": str(e)
        }