package com.project.request;

import com.project.model.Recipe;
import com.project.model.User;
import lombok.Data;

@Data
public class CreateRecipeRequest {
    private Recipe recipe;
    private User user;
}
