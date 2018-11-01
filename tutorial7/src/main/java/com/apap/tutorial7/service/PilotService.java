package com.apap.tutorial7.service;

import java.util.Optional;

import com.apap.tutorial7.model.PilotModel;

/**
 * PilotService
 */
public interface PilotService {
    Optional<PilotModel> getPilotDetailByLicenseNumber(String licenseNumber);

    PilotModel addPilot(PilotModel pilot);

    void deletePilotByLicenseNumber(String licenseNumber);
    
    void deletePilot(PilotModel pilot);

    Optional<PilotModel> getPilotDetailById(long id);
    
    void updatePilot(Long id, PilotModel pilot);
}