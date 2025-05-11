package org.tourneytrack.impl.business.service;

import org.tourneytrack.impl.business.mapper.*;
import org.tourneytrack.impl.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractServiceBase {

    @Autowired
    protected CompetitionDao competitionDao;

    @Autowired
    protected RuleSetDao ruleSetDao;

    @Autowired
    protected RuleDao ruleDao;

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected SubmissionDao submissionDao;

    @Autowired
    protected ScoreEntryDao scoreEntryDao;



    @Autowired
    protected CompetitionMapper competitionMapper;

    @Autowired
    protected ScoreEntryMapper scoreEntryMapper;

    @Autowired
    protected SubmissionMapper submissionMapper;

    @Autowired
    protected RuleSetMapper ruleSetMapper;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected ValidationService validationService;

}
