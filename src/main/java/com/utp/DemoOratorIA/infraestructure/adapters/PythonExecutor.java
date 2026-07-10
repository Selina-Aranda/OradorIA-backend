package com.utp.DemoOratorIA.infraestructure.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PythonExecutor {

    private static final Logger log = LoggerFactory.getLogger(PythonExecutor.class);
    private static final String PYTHON_SERVICE_DIR = "src/main/java/com/utp/DemoOratorIA/serviceIA";
    private static final String API_URL = "http://127.0.0.1:8000/analizar";
    private static final String HEALTH_URL = "http://127.0.0.1:8000/";
    private static final Duration REQUEST_TIMEOUT = Duration.ofMinutes(15);
    private static final Duration STARTUP_WAIT_TIMEOUT = Duration.ofMinutes(2);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public Map<String, Object> ejecutarAnalisis() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("duracion_segundos", 300);

        return ejecutarAnalisis(payload);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> ejecutarAnalisis(Map<String, Object> payload) throws Exception {
        log.info("🎯 Llamando a FastAPI para análisis...");

        asegurarFastApiActiva();

        String body = objectMapper.writeValueAsString(payload);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .timeout(REQUEST_TIMEOUT)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        
        HttpResponse<String> response = httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error en FastAPI: " + response.statusCode() + " - " + response.body());
        }
        
        String jsonResponse = response.body();
        log.info("📊 Respuesta de FastAPI: {}", jsonResponse);
        
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        
        if (!(Boolean) result.getOrDefault("success", false)) {
            throw new RuntimeException("Error en análisis: " + result.get("mensaje"));
        }
        
        return (Map<String, Object>) result.get("data");
    }

    public void detenerAnalisis() throws Exception {
        log.info("🛑 Deteniendo análisis en FastAPI...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8000/detener"))
                .timeout(REQUEST_TIMEOUT)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("📊 Respuesta detener FastAPI: {}", response.body());
    }

    private void asegurarFastApiActiva() throws Exception {
        if (isFastApiRunning()) {
            return;
        }

        log.info("🚀 FastAPI no estaba activa. Intentando iniciarla localmente...");

        File workingDirectory = new File(PYTHON_SERVICE_DIR);
        ProcessBuilder processBuilder = new ProcessBuilder(
                "py", "-3", "-m", "uvicorn", "api:app", "--host", "127.0.0.1", "--port", "8000"
        );
        processBuilder.directory(workingDirectory);
        processBuilder.redirectErrorStream(true);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo iniciar FastAPI localmente: " + e.getMessage(), e);
        }

        long deadline = System.currentTimeMillis() + STARTUP_WAIT_TIMEOUT.toMillis();
        while (System.currentTimeMillis() < deadline) {
            if (isFastApiRunning()) {
                return;
            }
            Thread.sleep(500);
        }

        throw new RuntimeException("FastAPI no respondió en el puerto 8000 después de iniciarla");
    }

    private boolean isFastApiRunning() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HEALTH_URL))
                    .timeout(Duration.ofSeconds(2))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() >= 200 && response.statusCode() < 500;
        } catch (Exception e) {
            return false;
        }
    }
}