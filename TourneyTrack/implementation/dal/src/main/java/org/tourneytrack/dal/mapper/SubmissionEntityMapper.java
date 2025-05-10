package org.tourneytrack.dal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tourneytrack.dal.entity.SubmissionEntity;
import org.tourneytrack.impl.data.Submission;

@Mapper(componentModel = "spring", uses = {
        UserEntityMapper.class,
        RuleSetEntityMapper.class
})
public interface SubmissionEntityMapper {
    Submission toModel(SubmissionEntity entity);
    SubmissionEntity toEntity(Submission model);
}
