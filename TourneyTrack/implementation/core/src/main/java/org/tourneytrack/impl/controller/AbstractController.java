package org.tourneytrack.impl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.tourneytrack.impl.business.service.*;
import org.tourneytrack.impl.dao.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AbstractController {

    @Autowired protected UserService userService;
    @Autowired protected CompetitionService competitionService;
    @Autowired protected RuleSetService ruleSetService;
    @Autowired protected SubmissionService submissionService;

    @Autowired protected PasswordEncoder passwordEncoder;

}
