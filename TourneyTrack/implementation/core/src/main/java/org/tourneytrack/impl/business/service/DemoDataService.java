package org.tourneytrack.impl.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DemoDataService extends AbstractServiceBase {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDemoData() {
        if (!userDao.findAll().isEmpty()) return;

        // ---- 1. USERS ----
        List<User> users = new ArrayList<>();
        users.add(userDao.save(newUser("Feri Bácsi", "gamemaster@hal.hu", UserType.GAME_MASTER)));
        users.add(userDao.save(newUser("Kis Pista", "pista.kis@peca.hu", UserType.PLAYER)));
        users.add(userDao.save(newUser("Nagy Gábor", "gabor.nagy@hal.hu", UserType.PLAYER)));
        users.add(userDao.save(newUser("Horgász Heni", "heni.horgasz@hal.hu", UserType.PLAYER)));
        users.add(userDao.save(newUser("Admin Béla", "admin.bela@peca.hu", UserType.GAME_MASTER)));
        users.add(userDao.save(newUser("Dzsózi", "jozsef.k@balin.hu", UserType.PLAYER)));
        users.add(userDao.save(newUser("Jani Bácsi", "jani.bacsi@halasz.hu", UserType.PLAYER)));
        users.add(userDao.save(newUser("Busa Zoli", "zoli.busa@balin.hu", UserType.GAME_MASTER)));

        // ---- 2. RULESETS + RULES (helyes sorrendben, nem külön DAO-val!) ----
        // Alap ruleset
        Rule alap1 = newRule("Halfogás 1kg felett", "Minden 1kg feletti halért pont jár.", 5);
        Rule ponty = newRule("Ponty fogása", "Ponty fogása extra pont.", 10);
        Rule harcsa = newRule("Harcsa fogása", "10kg feletti harcsa kiemelt pont.", 20);
        RuleSet basicRuleSet = new RuleSet();
        basicRuleSet.setName("Halfogás alap");
        basicRuleSet.setRules(new ArrayList<>(List.of(alap1, ponty, harcsa)));
        basicRuleSet = ruleSetDao.save(basicRuleSet);

        // Extra ruleset
        Rule compo = newRule("Compó fogása", "Compóért bónusz jár.", 7);
        Rule sullo = newRule("Süllő fogása", "Süllő fogása 12 pont.", 12);
        RuleSet extraRuleSet = new RuleSet();
        extraRuleSet.setName("Extra pontok");
        extraRuleSet.setRules(new ArrayList<>(List.of(compo, sullo)));
        extraRuleSet = ruleSetDao.save(extraRuleSet);

        // Gyerek ruleset
        Rule gyerek = newRule("Gyermek halfogás", "18 év alatt +2 pont minden halért.", 2);
        Rule cr = newRule("C&R fogás", "Fogd meg és engedd vissza!", 3);
        RuleSet gyerekSet = new RuleSet();
        gyerekSet.setName("Gyerek Kupa szabályok");
        gyerekSet.setRules(new ArrayList<>(List.of(gyerek, alap1, cr))); // új példányt kell létrehozni, vagy getRuleById
        gyerekSet = ruleSetDao.save(gyerekSet);

        // Pergető ruleset
        Rule pergeto = newRule("Pergetés", "Pergető módszerrel +4 pont.", 4);
        RuleSet pergetoSet = new RuleSet();
        pergetoSet.setName("Pergető Liga szabályok");
        pergetoSet.setRules(new ArrayList<>(List.of(pergeto, sullo, harcsa, alap1)));
        pergetoSet = ruleSetDao.save(pergetoSet);

        // FONTOS: most már id alapján lekérjük a mentett rule-okat, hogy referenciaként biztosan azokat használjuk:
        alap1 = ruleDao.getRuleById(basicRuleSet.getRules().get(0).getId());
        ponty = ruleDao.getRuleById(basicRuleSet.getRules().get(1).getId());
        harcsa = ruleDao.getRuleById(basicRuleSet.getRules().get(2).getId());
        compo = ruleDao.getRuleById(extraRuleSet.getRules().get(0).getId());
        sullo = ruleDao.getRuleById(extraRuleSet.getRules().get(1).getId());
        gyerek = ruleDao.getRuleById(gyerekSet.getRules().get(0).getId());
        cr = ruleDao.getRuleById(gyerekSet.getRules().get(2).getId());
        pergeto = ruleDao.getRuleById(pergetoSet.getRules().get(0).getId());

        // ---- 3. COMPETITIONS ----
        Date threeDays = new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000);
        Date fiveDays = new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000);
        Date yesterday = new Date(System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000);

// Újra, hogy biztosan mindenhol legalább 5 fő legyen!
        List<Competition> competitions = new ArrayList<>();
        competitions.add(competitionDao.save(newCompetition(
                "Pecás Kupa",
                "Minden, ami hal és horgászat.",
                users.get(0),
                List.of(users.get(1), users.get(2), users.get(3), users.get(5), users.get(6)),
                threeDays, true, List.of(basicRuleSet)
        )));
        competitions.add(competitionDao.save(newCompetition(
                "Ponty Feszt",
                "Csak ponty számít.",
                users.get(0),
                List.of(users.get(1), users.get(2), users.get(3), users.get(5), users.get(6)),
                fiveDays, true, List.of(basicRuleSet, extraRuleSet)
        )));
        competitions.add(competitionDao.save(newCompetition(
                "Gyerek Kupa",
                "18 év alattiaknak külön verseny.",
                users.get(4),
                List.of(users.get(1), users.get(2), users.get(3), users.get(6), users.get(5)),
                fiveDays, true, List.of(gyerekSet)
        )));
        competitions.add(competitionDao.save(newCompetition(
                "Pergető Liga",
                "Pergető specialistáknak.",
                users.get(7),
                List.of(users.get(3), users.get(5), users.get(1), users.get(2), users.get(6)),
                fiveDays, true, List.of(pergetoSet)
        )));
        competitions.add(competitionDao.save(newCompetition(
                "Lezárt Harcsa Bajnokság",
                "Lezárt harcsafogó verseny!",
                users.get(0),
                List.of(users.get(1), users.get(2), users.get(3), users.get(5), users.get(6)),
                yesterday, false, List.of(extraRuleSet)
        ))  );

// ---- 4. SUBMISSIONS ----
        List<Submission> submissions = List.of(
                newSubmission(users.get(1), alap1, competitions.get(0), "2kg ponty, Fenekező szerelékkel.", SubmissionStatus.APPROVED),
                newSubmission(users.get(2), ponty, competitions.get(0), "3.2kg ponty, kukorica csalival.", SubmissionStatus.PENDING),
                newSubmission(users.get(3), harcsa, competitions.get(0), "11kg harcsa, csontival.", SubmissionStatus.APPROVED),
                newSubmission(users.get(5), alap1, competitions.get(0), "1.2kg compó, kárászmix.", SubmissionStatus.REJECTED),

                newSubmission(users.get(1), compo, competitions.get(1), "Compó, sajtos etetővel.", SubmissionStatus.APPROVED),
                newSubmission(users.get(2), sullo, competitions.get(1), "Süllő, pergetve.", SubmissionStatus.PENDING),
                newSubmission(users.get(3), ponty, competitions.get(1), "Ponty, éjjeli horgászat.", SubmissionStatus.APPROVED),
                newSubmission(users.get(5), harcsa, competitions.get(1), "13kg harcsa, bojlival.", SubmissionStatus.APPROVED),

                newSubmission(users.get(1), gyerek, competitions.get(2), "Kis csuka, C&R.", SubmissionStatus.APPROVED),
                newSubmission(users.get(2), alap1, competitions.get(2), "1.3kg keszeg.", SubmissionStatus.PENDING),
                newSubmission(users.get(3), cr, competitions.get(2), "Fogd meg és engedd vissza!", SubmissionStatus.APPROVED),
                newSubmission(users.get(5), alap1, competitions.get(2), "Fiatal ponty, visszaengedve.", SubmissionStatus.REJECTED),

                newSubmission(users.get(3), pergeto, competitions.get(3), "Pergető, extra pont.", SubmissionStatus.APPROVED),
                newSubmission(users.get(5), sullo, competitions.get(3), "Balatoni süllő, pergetve.", SubmissionStatus.PENDING),
                newSubmission(users.get(1), alap1, competitions.get(3), "2kg süllő.", SubmissionStatus.APPROVED),
                newSubmission(users.get(2), cr, competitions.get(3), "Fogd meg és engedd vissza.", SubmissionStatus.APPROVED),

                newSubmission(users.get(1), harcsa, competitions.get(4), "Lezárás előtti rekord!", SubmissionStatus.APPROVED),
                newSubmission(users.get(2), sullo, competitions.get(4), "Utolsó nap, süllő.", SubmissionStatus.PENDING),
                newSubmission(users.get(3), compo, competitions.get(4), "Záró fogás, compó.", SubmissionStatus.APPROVED),
                newSubmission(users.get(5), harcsa, competitions.get(4), "Harcsa, archív.", SubmissionStatus.REJECTED)
        );
        submissions.forEach(submissionDao::save);

        // ---- 5. SCORE ENTRIES ----
        List<ScoreEntry> entries = List.of(
                // Pecás Kupa
                newScoreEntry(users.get(1), alap1, competitions.get(0), alap1.getPoints()),
                newScoreEntry(users.get(2), ponty, competitions.get(0), ponty.getPoints()),
                newScoreEntry(users.get(3), harcsa, competitions.get(0), harcsa.getPoints()),
                newScoreEntry(users.get(5), alap1, competitions.get(0), alap1.getPoints()),

                // Ponty Feszt
                newScoreEntry(users.get(1), compo, competitions.get(1), compo.getPoints()),
                newScoreEntry(users.get(2), sullo, competitions.get(1), sullo.getPoints()),
                newScoreEntry(users.get(3), ponty, competitions.get(1), ponty.getPoints()),
                newScoreEntry(users.get(5), harcsa, competitions.get(1), harcsa.getPoints()),

                // Gyerek Kupa
                newScoreEntry(users.get(1), gyerek, competitions.get(2), gyerek.getPoints()),
                newScoreEntry(users.get(2), alap1, competitions.get(2), alap1.getPoints()),
                newScoreEntry(users.get(3), cr, competitions.get(2), cr.getPoints()),
                newScoreEntry(users.get(5), alap1, competitions.get(2), alap1.getPoints()),

                // Pergető Liga
                newScoreEntry(users.get(3), pergeto, competitions.get(3), pergeto.getPoints()),
                newScoreEntry(users.get(5), sullo, competitions.get(3), sullo.getPoints()),
                newScoreEntry(users.get(1), alap1, competitions.get(3), alap1.getPoints()),
                newScoreEntry(users.get(2), cr, competitions.get(3), cr.getPoints()),

                // Lezárt Harcsa Bajnokság
                newScoreEntry(users.get(1), harcsa, competitions.get(4), harcsa.getPoints()),
                newScoreEntry(users.get(2), sullo, competitions.get(4), sullo.getPoints()),
                newScoreEntry(users.get(3), compo, competitions.get(4), compo.getPoints()),
                newScoreEntry(users.get(5), harcsa, competitions.get(4), harcsa.getPoints())
        );
        entries.forEach(scoreEntryDao::save);

        System.out.println("------ DEMO ADATOK BETÖLTVE ------");

        // === TÖBB ADAT: PLUSZ USERS, VERSENYEK, SUBMISSIONS, SCORE ENTRIES ===

        // Extra users
        User pista2 = userDao.save(newUser("Németh Pista", "nemeth.pista@balaton.hu", UserType.PLAYER));
        User feri2 = userDao.save(newUser("Fekete Feri", "feri.fekete@balin.hu", UserType.PLAYER));
        User kata = userDao.save(newUser("Kata", "kata.horgasz@peca.hu", UserType.PLAYER));
        User viki = userDao.save(newUser("Viki", "viki@balaton.hu", UserType.PLAYER));
        User denes = userDao.save(newUser("Dénes", "denes@peca.hu", UserType.PLAYER));
        User gyuszi = userDao.save(newUser("Gyuszi", "gyuszi@balin.hu", UserType.PLAYER));

        // Új versenyek, változatosabb ruleset kombinációkkal
        Competition nightFishing = competitionDao.save(newCompetition(
                "Éjszakai Pecás Challenge",
                "Éjszakai halfogás, külön díjazással.",
                users.get(0),
                List.of(pista2, users.get(1), users.get(3), denes),
                new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000),
                true,
                List.of(basicRuleSet, extraRuleSet)
        ));

        Competition balatonOpen = competitionDao.save(newCompetition(
                "Balaton Open",
                "Balatoni ponty és süllő!",
                users.get(4),
                List.of(users.get(2), viki, kata, users.get(5), users.get(3)),
                new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000),
                true,
                List.of(basicRuleSet, pergetoSet)
        ));

        Competition gyerekKupa2 = competitionDao.save(newCompetition(
                "Junior Kupa II.",
                "Külön gyerek mezőny, családias hangulat.",
                users.get(4),
                List.of(gyuszi, kata, viki, users.get(6)),
                new Date(System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000),
                true,
                List.of(gyerekSet)
        ));

        Competition lezartPontyKupa = competitionDao.save(newCompetition(
                "Lezárt Ponty Kupa",
                "Tavalyi pontyfogó verseny - archív!",
                users.get(7),
                List.of(users.get(1), pista2, feri2),
                yesterday,
                false,
                List.of(basicRuleSet, extraRuleSet)
        ));

        // Extra submissions
        List<Submission> extraSubmissions = List.of(
                newSubmission(pista2, alap1, nightFishing, "2.3kg ponty, kukorica csalival.", SubmissionStatus.PENDING),
                newSubmission(pista2, ponty, nightFishing, "6kg ponty, éjszaka fogva.", SubmissionStatus.APPROVED),
                newSubmission(denes, compo, nightFishing, "1.5kg compó, süllőzés közben.", SubmissionStatus.REJECTED),
                newSubmission(users.get(3), sullo, nightFishing, "Éjjeli süllő, pergetve.", SubmissionStatus.APPROVED),

                newSubmission(users.get(2), ponty, balatonOpen, "5.1kg ponty, spiccbottal.", SubmissionStatus.PENDING),
                newSubmission(viki, sullo, balatonOpen, "Balatoni süllő, klasszik pergetés.", SubmissionStatus.APPROVED),
                newSubmission(kata, alap1, balatonOpen, "1.2kg keszeg, gyerek bottal.", SubmissionStatus.APPROVED),
                newSubmission(users.get(5), cr, balatonOpen, "Keszeg release.", SubmissionStatus.REJECTED),

                newSubmission(gyuszi, gyerek, gyerekKupa2, "20cm kárász, első horgászat!", SubmissionStatus.APPROVED),
                newSubmission(users.get(6), cr, gyerekKupa2, "Fogd meg és engedd vissza!", SubmissionStatus.PENDING),
                newSubmission(kata, alap1, gyerekKupa2, "1kg ponty, szülői segítséggel.", SubmissionStatus.APPROVED),

                newSubmission(users.get(1), alap1, lezartPontyKupa, "Tavalyi rekord!", SubmissionStatus.APPROVED),
                newSubmission(pista2, ponty, lezartPontyKupa, "Régi fogás, 4.5kg.", SubmissionStatus.PENDING)
        );
        extraSubmissions.forEach(submissionDao::save);

        // Extra score entries
        List<ScoreEntry> extraScores = List.of(
                newScoreEntry(pista2, alap1, nightFishing, alap1.getPoints()),
                newScoreEntry(pista2, ponty, nightFishing, ponty.getPoints()),
                newScoreEntry(users.get(3), sullo, nightFishing, sullo.getPoints()),

                newScoreEntry(users.get(2), ponty, balatonOpen, ponty.getPoints()),
                newScoreEntry(viki, sullo, balatonOpen, sullo.getPoints()),
                newScoreEntry(kata, alap1, balatonOpen, alap1.getPoints()),

                newScoreEntry(gyuszi, gyerek, gyerekKupa2, gyerek.getPoints()),
                newScoreEntry(kata, alap1, gyerekKupa2, alap1.getPoints()),

                newScoreEntry(users.get(1), alap1, lezartPontyKupa, alap1.getPoints()),
                newScoreEntry(pista2, ponty, lezartPontyKupa, ponty.getPoints())
        );
        extraScores.forEach(scoreEntryDao::save);

        System.out.println("------ EXTRA DEMO ADATOK BETÖLTVE ------");
    }


    // Helper methods
    private User newUser(String name, String email, UserType type) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode("12345678"));
        u.setType(type);
        return u;
    }

    private Rule newRule(String name, String desc, int points) {
        Rule r = new Rule();
        r.setName(name);
        r.setDescription(desc);
        r.setPoints(points);
        return r;
    }

    private Competition newCompetition(String name, String desc, User gm, List<User> parts, Date deadline, boolean prog, List<RuleSet> sets) {
        Competition c = new Competition();
        c.setName(name);
        c.setDescription(desc);
        c.setGameMaster(gm);
        c.setParticipants(parts);
        c.setDeadline(deadline);
        c.setInProgress(prog);
        c.setRuleSets(sets);
        return c;
    }

    private Submission newSubmission(User user, Rule rule, Competition comp, String desc, SubmissionStatus status) {
        Submission s = new Submission();
        s.setUser(user);
        s.setRule(rule);
        s.setCompetitionId(comp.getId());
        s.setDescription(desc);
        s.setSubmittedAt(new Date());
        s.setStatus(status);
        return s;
    }

    private ScoreEntry newScoreEntry(User player, Rule rule, Competition comp, int points) {
        ScoreEntry se = new ScoreEntry();
        se.setPlayer(player);
        se.setRule(rule);
        se.setCompetitionId(comp.getId());
        se.setPointsEarned(points);
        return se;
    }

}
