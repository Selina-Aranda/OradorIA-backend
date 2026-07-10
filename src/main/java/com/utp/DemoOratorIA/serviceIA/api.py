from fastapi import FastAPI
from pydantic import BaseModel
from typing import Optional
from analisis_oratoria import main

app = FastAPI()

class AnalisisRequest(BaseModel):
    duracion_segundos: Optional[int] = 300

@app.get("/")
def read_root():
    return {"status": "ok"}

@app.post("/analizar")
def analizar(req: Optional[AnalisisRequest] = None):
    config = {}
    if req:
        config["duracion_segundos"] = req.duracion_segundos
    else:
        config["duracion_segundos"] = 300
        
    resultado = main(config)
    return {
        "success": True,
        "data": resultado
    }

@app.post("/detener")
def detener():
    from grabador import detener_grabacion
    detener_grabacion()
    return {
        "success": True,
        "mensaje": "Grabación detenida"
    }