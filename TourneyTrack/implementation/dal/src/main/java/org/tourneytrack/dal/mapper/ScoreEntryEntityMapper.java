package org.tourneytrack.dal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tourneytrack.dal.entity.ScoreEntryEntity;
import org.tourneytrack.impl.data.ScoreEntry;

@Mapper(componentModel = "spring", uses = {
    UserEntityMapper.class,
    RuleEntityMapper.class
})
public interface ScoreEntryEntityMapper {

    @Mapping(source = "scoreBoardId", target = "scoreBoardId")
    ScoreEntryEntity toEntity(ScoreEntry model);

    @Mapping(source = "scoreBoardId", target = "scoreBoardId")
    ScoreEntry toModel(ScoreEntryEntity entity);
}
