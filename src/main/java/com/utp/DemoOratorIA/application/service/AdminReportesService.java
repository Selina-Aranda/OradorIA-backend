package com.utp.DemoOratorIA.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.infraestructure.DTO.AdminReporteFinancieroDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.AdminReporteIADTO;
import com.utp.DemoOratorIA.infraestructure.DTO.AdminReporteUsuariosDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.ConteoDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.MontoDTO;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAAnalisisRepository;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAPagoRepository;
import com.utp.DemoOratorIA.infraestructure.repositories.JPASuscripcionesRepository;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAUserRepository;
import com.utp.DemoOratorIA.infraestructure.repositories.ResultadoQueryRepository;

@Service
public class AdminReportesService {

    private static final String[] NOMBRES_MES = {
            "Ene", "Feb", "Mar", "Abr", "May", "Jun",
            "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
    };

    private final JPAUserRepository userRepository;
    private final JPAAnalisisRepository analisisRepository;
    private final ResultadoQueryRepository resultadoQueryRepository;
    private final JPAPagoRepository pagoRepository;
    private final JPASuscripcionesRepository suscripcionesRepository;

    public AdminReportesService(
            JPAUserRepository userRepository,
            JPAAnalisisRepository analisisRepository,
            ResultadoQueryRepository resultadoQueryRepository,
            JPAPagoRepository pagoRepository,
            JPASuscripcionesRepository suscripcionesRepository) {

        this.userRepository = userRepository;
        this.analisisRepository = analisisRepository;
        this.resultadoQueryRepository = resultadoQueryRepository;
        this.pagoRepository = pagoRepository;
        this.suscripcionesRepository = suscripcionesRepository;
    }

    // ==================== REPORTE DE USUARIOS ====================

    public AdminReporteUsuariosDTO getReporteUsuarios() {

        long totalUsuarios = userRepository.count();

        long usuariosActivos = userRepository.contarPorEstado().stream()
                .filter(f -> "ACTIVO".equalsIgnoreCase(String.valueOf(f[0])))
                .mapToLong(f -> ((Number) f[1]).longValue())
                .findFirst()
                .orElse(0L);

        Long nuevosEsteMes = userRepository.contarNuevosEsteMes();

        List<ConteoDTO> porEstado = userRepository.contarPorEstado().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        List<ConteoDTO> porPlan = userRepository.contarPorPlan().stream()
                .map(f -> new ConteoDTO(
                        f[0] != null ? String.valueOf(f[0]) : "Sin plan",
                        ((Number) f[1]).longValue()))
                .toList();

        List<ConteoDTO> registrosPorMes = userRepository.registrosPorMes().stream()
                .map(this::mapearConteoMensual)
                .toList();

        return new AdminReporteUsuariosDTO(
                totalUsuarios,
                usuariosActivos,
                nuevosEsteMes != null ? nuevosEsteMes : 0L,
                porEstado,
                porPlan,
                registrosPorMes
        );
    }

    // ==================== REPORTE IA (uso + rendimiento) ====================

    public AdminReporteIADTO getReporteIA() {

        Long totalAnalisis = analisisRepository.contarTotal();

        List<Object[]> promediosFila = resultadoQueryRepository.promediosGlobales();

        Double promedioPuntuacion = 0.0, promedioFluidez = 0.0, promedioClaridad = 0.0,
                promedioConfianza = 0.0, promedioMuletillas = 0.0;

        if (!promediosFila.isEmpty() && promediosFila.get(0)[0] != null) {
            Object[] fila = promediosFila.get(0);
            promedioPuntuacion = redondear(fila[0]);
            promedioFluidez = redondear(fila[1]);
            promedioClaridad = redondear(fila[2]);
            promedioConfianza = redondear(fila[3]);
            promedioMuletillas = redondear(fila[4]);
        }

        List<ConteoDTO> porEstado = analisisRepository.contarPorEstado().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        List<ConteoDTO> porNivel = resultadoQueryRepository.contarPorNivel().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        List<ConteoDTO> evolucionMensual = analisisRepository.evolucionMensualGlobal().stream()
                .map(this::mapearConteoMensual)
                .toList();

        List<ConteoDTO> topMuletillas = resultadoQueryRepository.topMuletillas().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        return new AdminReporteIADTO(
                totalAnalisis != null ? totalAnalisis : 0L,
                promedioPuntuacion,
                promedioFluidez,
                promedioClaridad,
                promedioConfianza,
                promedioMuletillas,
                porEstado,
                porNivel,
                evolucionMensual,
                topMuletillas
        );
    }

    // ==================== REPORTE FINANCIERO ====================

    public AdminReporteFinancieroDTO getReporteFinanciero() {

        Double ingresosTotales = pagoRepository.ingresosTotales();

        LocalDate hoy = LocalDate.now();
        Double ingresosEsteMes = pagoRepository.obtenerIngresosMensuales(hoy.getYear(), hoy.getMonthValue());

        Long suscripcionesActivas = suscripcionesRepository.contarActivas();

        List<MontoDTO> ingresosPorMes = pagoRepository.ingresosPorMes().stream()
                .map(this::mapearMontoMensual)
                .toList();

        List<MontoDTO> ingresosPorPlan = pagoRepository.ingresosPorPlan().stream()
                .map(f -> new MontoDTO(
                        f[0] != null ? String.valueOf(f[0]) : "Sin plan",
                        redondear(f[1])))
                .toList();

        List<ConteoDTO> pagosPorMetodo = pagoRepository.pagosPorMetodo().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        List<ConteoDTO> pagosPorEstado = pagoRepository.pagosPorEstado().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        List<ConteoDTO> suscripcionesPorEstado = suscripcionesRepository.contarPorEstado().stream()
                .map(f -> new ConteoDTO(String.valueOf(f[0]), ((Number) f[1]).longValue()))
                .toList();

        return new AdminReporteFinancieroDTO(
                redondear(ingresosTotales),
                redondear(ingresosEsteMes),
                suscripcionesActivas != null ? suscripcionesActivas : 0L,
                ingresosPorMes,
                ingresosPorPlan,
                pagosPorMetodo,
                pagosPorEstado,
                suscripcionesPorEstado
        );
    }

    // ==================== HELPERS ====================

    private ConteoDTO mapearConteoMensual(Object[] fila) {
        int mes = ((Number) fila[1]).intValue();
        String etiqueta = NOMBRES_MES[mes - 1] + " " + fila[0];
        return new ConteoDTO(etiqueta, ((Number) fila[2]).longValue());
    }

    private MontoDTO mapearMontoMensual(Object[] fila) {
        int mes = ((Number) fila[1]).intValue();
        String etiqueta = NOMBRES_MES[mes - 1] + " " + fila[0];
        return new MontoDTO(etiqueta, redondear(fila[2]));
    }

    private Double redondear(Object valor) {
        if (valor == null) {
            return 0.0;
        }
        double d = ((Number) valor).doubleValue();
        return Math.round(d * 100.0) / 100.0;
    }
}