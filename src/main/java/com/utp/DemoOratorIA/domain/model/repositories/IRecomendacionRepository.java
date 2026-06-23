package com.utp.DemoOratorIA.domain.model.repositories;

import java.util.List;
import com.utp.DemoOratorIA.domain.model.aggregate.Recomendacion;

public interface IRecomendacionRepository {

    Recomendacion save(Recomendacion rec);

    Recomendacion findById(Integer id);

    Recomendacion update(Recomendacion rec);

    List<Recomendacion> list();

    void delete(Integer id);
}