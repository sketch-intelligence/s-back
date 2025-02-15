package com.website.e_commerce.userproject;

import java.util.List;

public interface IUserProjectService {
    UserProjectDto createUserProject(UserProjectDto userProjectDto);
    UserProjectDto getUserProjectById(Long id);
    List<UserProjectDto> getAllUserProjects();
    UserProjectDto updateUserProject(Long id, UserProjectDto userProjectDto);
    void deleteUserProject(Long id);

    UserProject acceptBid(Long projectId, Long bidId, Long userId);
}
