package org.tourneytrack.impl.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tourneytrack.impl.data.Submission;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    SubmissionDto toDto(Submission submission);

    Submission toModel(SubmissionDto submissionDto);

}
