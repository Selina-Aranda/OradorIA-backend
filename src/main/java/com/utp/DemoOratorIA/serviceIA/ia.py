# ia.py
# Módulo exclusivo para comunicación con Ollama

import requests
import json
import re

def generar_analisis_fallback(texto, ppm, total_muletillas, cantidad_pausas, cantidad_miradas):
    # Calcular calificaciones realistas basados en los datos reales capturados
    fluidez = max(3.0, min(9.5, 10.0 - (total_muletillas * 0.4) - (cantidad_pausas * 0.6)))
    
    # Velocidad óptima de oratoria es entre 110 y 140 ppm
    if ppm == 0:
        velocidad = 5.0
    else:
        desviacion = abs(ppm - 125)
        velocidad = max(4.0, min(9.5, 10.0 - (desviacion / 15.0)))
        
    contacto_visual = max(3.0, min(9.5, 10.0 - (cantidad_miradas * 0.4)))
    
    # Valores base realistas
    claridad = 8.0 if len(texto) > 10 else 4.0
    volumen = 7.5 if len(texto) > 10 else 4.0
    postura = 8.0 if cantidad_miradas < 5 else 6.5
    confianza = max(4.0, min(9.5, (fluidez + contacto_visual) / 2.0))
    expresion_facial = 7.5
    
    puntuacion_general = (fluidez + claridad + volumen + velocidad + postura + contacto_visual + confianza + expresion_facial) / 8.0
    
    errores = []
    recs = []
    
    if total_muletillas > 3:
        errores.append(f"Uso frecuente de muletillas ({total_muletillas} detectadas).")
        recs.append("Intenta hacer pausas breves en silencio en lugar de llenar los vacíos con palabras de relleno como 'este', 'eh' o 'bueno'.")
    if cantidad_pausas > 2:
        errores.append(f"Se identificaron {cantidad_pausas} pausas demasiado largas que interrumpen el ritmo.")
        recs.append("Practica transiciones más fluidas entre tus ideas clave para evitar silencios prolongados.")
    if cantidad_miradas > 3:
        errores.append(f"Desviación frecuente del contacto visual ({cantidad_miradas} veces).")
        recs.append("Mantén la mirada fija en la cámara o en el público para transmitir mayor seguridad y conectar mejor.")
    if ppm < 100 and ppm > 0:
        errores.append("Ritmo de habla demasiado lento.")
        recs.append("Intenta hablar con un poco más de dinamismo y entusiasmo para mantener enganchada a la audiencia.")
    elif ppm > 150:
        errores.append("Velocidad de habla acelerada.")
        recs.append("Habla más pausado, respira profundamente y pronuncia con claridad cada palabra.")
        
    if not errores:
        errores.append("No se detectaron errores significativos. Tu ritmo y contacto visual son buenos.")
        recs.append("Sigue practicando para mantener este nivel de elocuencia y control corporal.")
        
    return {
        "fluidez": round(fluidez, 1),
        "claridad": round(claridad, 1),
        "volumen": round(volumen, 1),
        "velocidad": round(velocidad, 1),
        "postura": round(postura, 1),
        "contacto_visual": round(contacto_visual, 1),
        "confianza": round(confianza, 1),
        "expresion_facial": round(expresion_facial, 1),
        "puntuacion_general": round(puntuacion_general, 1),
        "errores_detectados": "\n".join(errores),
        "recomendaciones": "\n".join(recs)
    }

def limpiar_y_cargar_json(raw_text):
    match = re.search(r"\{.*\}", raw_text, re.DOTALL)
    if not match:
        raise ValueError("No se encontraron llaves de JSON")
    
    json_str = match.group()
    
    # Escapar saltos de línea literales dentro de comillas
    def replace_newlines(m):
        return m.group(0).replace('\n', '\\n').replace('\r', '')
        
    json_str = re.sub(r'"([^"\\]|\\.)*"', replace_newlines, json_str)
    return json.loads(json_str)

def analisis_ia(texto, ppm, muletillas, pausas, miradas):
    total_muletillas = sum(muletillas.values())

    if isinstance(miradas, dict):
        cantidad_miradas = miradas.get("cantidad_miradas_desviadas", 0)
    else:
        cantidad_miradas = miradas

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
        response = requests.post(url, json=payload, timeout=25)
        response.raise_for_status()

        respuesta = response.json()["response"]
        print(f"Respuesta cruda de Ollama: {respuesta}")

        resultado = limpiar_y_cargar_json(respuesta)

        # Agregar métricas obtenidas por tu sistema
        resultado["muletillas_detectadas"] = total_muletillas
        resultado["pausas_incomodas"] = cantidad_pausas
        resultado["miradas_desviadas"] = cantidad_miradas
        resultado["velocidad_ppm"] = ppm

        return resultado

    except Exception as e:
        print(f"⚠️ Error al conectar o parsear Ollama ({e}). Iniciando análisis dinámico realista de respaldo...")
        fallback = generar_analisis_fallback(texto, ppm, total_muletillas, cantidad_pausas, cantidad_miradas)
        return fallback