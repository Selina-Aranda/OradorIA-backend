from fastapi import FastAPI
from analisis_oratoria import main


app = FastAPI()


@app.post("/analizar")

def analizar():
    resultado = main()
    return {
        "success": True,
        "data": resultado
    }