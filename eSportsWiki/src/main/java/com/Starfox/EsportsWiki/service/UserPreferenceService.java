package com.Starfox.EsportsWiki.service;

import com.Starfox.EsportsWiki.dto.UserPreferenceDTO;
import com.Starfox.EsportsWiki.model.UserPreference;
import com.Starfox.EsportsWiki.repository.UserPreferencesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {
    private final UserPreferencesDAO userPreferencesDAO;
    
    @Autowired
    public UserPreferenceService(UserPreferencesDAO userPreferencesDAO) {
        this.userPreferencesDAO = userPreferencesDAO;
    }
    
    public void saveUserPreferences(UserPreferenceDTO userPreferenceDTO) {
        UserPreference userPreference = new UserPreference(
            userPreferenceDTO.getUserId(),
            userPreferenceDTO.isLiveData(),
            userPreferenceDTO.isTeamData(),
            userPreferenceDTO.isPlayerData()
        );
        userPreferencesDAO.saveUserPreferences(userPreference.getUserId(), userPreference.isLiveData(), userPreference.isTeamData(), userPreference.isPlayerData());
    }
    
    public UserPreferenceDTO getUserPreferences(int userId) {
        boolean[] preferences = userPreferencesDAO.getUserPreferences(userId);
        if (preferences != null) {
            return new UserPreferenceDTO(
                userId,
                preferences[0],
                preferences[1],
                preferences[2]
            );
        }
        return null;
    }
}