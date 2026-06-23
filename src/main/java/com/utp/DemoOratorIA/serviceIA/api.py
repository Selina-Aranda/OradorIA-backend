from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def inicio():
    return {"mensaje": "OradorIA API funcionando"}

@app.get("/analizar")
def analizar():
    return ejecutar_analisis()