package com.project.service.recipe;

import com.project.dto.ImageDto;
import com.project.dto.RecipeDto;
import com.project.dto.ReviewDto;
import com.project.dto.UserDto;
import com.project.model.Image;
import com.project.model.Recipe;
import com.project.model.User;
import com.project.repository.ImageRepository;
import com.project.repository.RecipeRepository;
import com.project.repository.ReviewRepository;
import com.project.repository.UserRepository;
import com.project.request.CreateRecipeRequest;
import com.project.request.RecipeUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeService implements IRecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Recipe createRecipe(CreateRecipeRequest request) {
        if(request == null || request.getUser() == null){
            throw new IllegalArgumentException("Invalid request");
        }
        User user = Optional.ofNullable(userRepository.findByUsername(request.getUser().getUsername()))
                .map(existingUser -> {
                    existingUser.getRecipe().add(request.getRecipe());
                    return existingUser;
                }).orElseGet(() -> {
                    User newUser = new User(request.getUser().getUsername());
                    userRepository.save(newUser);
                    return newUser;
                });
        Recipe recipe = IRecipeService.createRecipe(request, user);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(RecipeUpdateRequest request, Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        Recipe theRecipe = IRecipeService.updateRecipe(recipe, request);
        return recipeRepository.save(theRecipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);

    }

    @Override
    public Set<String> getAllRecipeCategories() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe :: getCategory)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllRecipeCuisine() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe :: getCuisine)
                .collect(Collectors.toSet());
    }
    @Override
     public List<RecipeDto> getConvertedRecipes(List<Recipe> recipes){
        return recipes.stream().map(this :: convertToDto).toList();
     }

     @Override
    public RecipeDto convertToDto(Recipe recipe){
        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
        UserDto userDto = modelMapper.map(recipe.getUser(), UserDto.class);
        Optional<Image> image  = Optional.ofNullable(imageRepository.findByRecipeId(recipe.getId()));
        image.map(img -> modelMapper.map(img, ImageDto.class)).ifPresent(recipeDto ::setImageDto);
        List<ReviewDto> reviews = reviewRepository.findAllByRecipeId(recipe.getId())
                .stream()
                .map(review -> modelMapper.map(review, ReviewDto.class)).toList();

      //  double averageReviews = recipe.calculateAverageRatings();
       // int totalRateCount = recipe.getTotalRateCount();

         recipeDto.setTotalRateCount(recipe.getTotalRateCount());
         recipeDto.setAverageRating(recipe.calculateAverageRatings());

        recipeDto.setUser(userDto);
        recipeDto.setReviews(reviews);
        return recipeDto;
    }
}
