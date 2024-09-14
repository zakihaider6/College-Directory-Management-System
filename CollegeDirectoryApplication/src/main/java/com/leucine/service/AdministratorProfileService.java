package com.leucine.service;

import com.leucine.dto.AdministratorProfileDTO;
import com.leucine.modal.AdministratorProfile;

public interface AdministratorProfileService {
	public AdministratorProfile createAdministrator(Integer id,AdministratorProfileDTO administratorProfileDTO);
	
}
