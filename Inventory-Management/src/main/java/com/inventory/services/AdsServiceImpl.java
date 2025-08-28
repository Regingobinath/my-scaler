package com.inventory.services;

import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.Advertisement;
import com.inventory.models.Preference;
import com.inventory.models.User;
import com.inventory.repositories.AdvertisementRepository;
import com.inventory.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService{
    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;

    public AdsServiceImpl(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    @Transactional
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException {
        Optional<User> userOpt = this.userRepository.findById(userId);
        User user = userOpt.orElseThrow(() -> new UserNotFoundException("User Not found"));

        List<Preference> userPreferences = user.getPreferences();
        for (Preference preference : userPreferences) {
            Optional<Advertisement> optionalAdvertisement = this.advertisementRepository.findByPreference(preference);
            if (optionalAdvertisement.isPresent()) {
                return optionalAdvertisement.get();
            }
        }

        List<Advertisement> advertisements = this.advertisementRepository.findAll();
        return advertisements.isEmpty() ? null : advertisements.get(0);
    }
}
