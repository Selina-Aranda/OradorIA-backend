package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Plan;
import com.utp.DemoOratorIA.domain.model.repositories.IPlanRepository;

@Service
public class PlanService {

    private final IPlanRepository planRepository;

    public PlanService(IPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public List<Plan> listar() {
        return planRepository.list();
    }

    public Plan findById(Integer id) {
        return planRepository.findById(id);
    }

    public Plan update(Plan plan) {
        return planRepository.update(plan);
    }

    public void delete(Integer id) {
        planRepository.delete(id);
    }
}