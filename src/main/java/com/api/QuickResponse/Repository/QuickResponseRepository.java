package com.api.QuickResponse.Repository;

public class QuickResponseRepository {
    public QuickResponseRepository() {
    }

    public static QuickResponseRepository quickResponseRepository;

    public static QuickResponseRepository getQuickResponseRepository() {
        return quickResponseRepository;
    }

    public static void setQuickResponseRepository(QuickResponseRepository quickResponseRepository) {
        QuickResponseRepository.quickResponseRepository = quickResponseRepository;
    }

}
