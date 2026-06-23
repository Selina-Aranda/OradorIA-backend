package com.utp.DemoOratorIA.domain.model.repositories;

import java.util.List;
import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;

public interface IResultadoIARepository {

    ResultadoIA save(ResultadoIA res);

    ResultadoIA findById(Integer id);

    ResultadoIA update(ResultadoIA res);

    List<ResultadoIA> list();

    void delete(Integer id);
}