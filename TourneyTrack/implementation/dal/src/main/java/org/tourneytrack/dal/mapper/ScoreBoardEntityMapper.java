package org.tourneytrack.dal.mapper;

import org.mapstruct.Mapper;
import org.tourneytrack.dal.entity.ScoreBoardEntity;
import org.tourneytrack.impl.data.ScoreBoard;

@Mapper(componentModel = "spring", uses = {ScoreEntryEntityMapper.class})
public interface ScoreBoardEntityMapper {
    ScoreBoard toModel(ScoreBoardEntity entity);
    ScoreBoardEntity toEntity(ScoreBoard model);
}
