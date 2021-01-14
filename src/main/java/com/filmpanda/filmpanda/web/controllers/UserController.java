package com.filmpanda.filmpanda.web.controllers;

import com.filmpanda.filmpanda.domain.models.binding.UserEditBindingModel;
import com.filmpanda.filmpanda.domain.models.binding.UserRegisterBindingModel;
import com.filmpanda.filmpanda.domain.models.service.UserServiceModel;
import com.filmpanda.filmpanda.domain.models.view.UserAdminListViewModel;
import com.filmpanda.filmpanda.domain.models.view.UserProfileViewModel;
import com.filmpanda.filmpanda.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        super(userService, modelMapper);
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Loads a view of the Register page.
     * @param modelAndView allows us to attach "Register" to the title (e.g. "Register - FilmPanda").
     * @return view of the Register page.
     * @see BaseController#view(String, ModelAndView)
     */
    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.addObject("pageTitle", "Register");
        return view("register", modelAndView);
    }

    /**
     * Checks whether 'password' and 'confirmPassword' match in the form. If so, attempts to register the user into the database. Redirects to login.
     * @param model contains all the data that was submitted in the registration form. Not needed as of now, but consistency is important.
     * @param modelAndView allows us to attach "Register" to the title (e.g. "Register - FilmPanda").
     * @return view of the Register page if passwords don't match, otherwise view of the Login page.
     * @see com.filmpanda.filmpanda.service.UserService#registerUser(UserServiceModel)
     * @see BaseController#view(String, ModelAndView)
     */
    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute(name = "model") UserRegisterBindingModel model, ModelAndView modelAndView) {
        try {
            if (!model.getPassword().equals(model.getConfirmPassword())) {
                modelAndView.addObject("pageTitle", "Register");
                return view("register", modelAndView);
            }
            this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));
            return redirect("login");
        } catch (Exception e) {
            return redirect("register");
        }
    }

    /**
     * Loads a view of the Login page.
     * @param modelAndView allows us to attach "Login" to the title (e.g. "Login - FilmPanda").
     * @return view of the Login page.
     * @see BaseController#view(String, ModelAndView)
     */
    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.addObject("pageTitle", "Login");
        return view("login", modelAndView);
    }

    /**
     * Finds the current user's data by their username (using the Principle class). Loads a view of the current user's Profile page.
     * @param modelAndView allows us to attach "#[currentUser]" to the title (e.g. "#Kyan7 - FilmPanda").
     * @return view of the current user's Profile page.
     * @see BaseController#view(String, ModelAndView)
     */
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
        UserProfileViewModel userProfileViewModel = this.modelMapper
                .map(this.userService.findUserByUsername(principal.getName()), UserProfileViewModel.class);
        modelAndView.addObject("model", userProfileViewModel);
        modelAndView.addObject("pageTitle", "#" + userProfileViewModel.getUsername());
        return view("profile", modelAndView);
    }

    /**
     * Finds the current user's data by their username (using the Principle class). Loads a view of the current user's Edit Profile page (which allows certain changes to their data).
     * @param modelAndView allows us to attach "Edit #[currentUser]" to the title (e.g. "Edit #Kyan7 - FilmPanda").
     * @return view of the current user's Edit Profile page.
     * @see BaseController#view(String, ModelAndView)
     */
    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        UserProfileViewModel userProfileViewModel = this.modelMapper
                .map(this.userService.findUserByUsername(principal.getName()), UserProfileViewModel.class);
        modelAndView.addObject("model", userProfileViewModel);
        modelAndView.addObject("pageTitle", "Edit #" + userProfileViewModel.getUsername());
        return view("edit-profile", modelAndView);
    }

    /**
     * Checks whether 'password' and 'confirmPassword' match in the form. If so, attempts to edit the current user's data. Redirects to the current user's profile.
     * @param model contains all the data that was submitted in the edit profile form.
     * @param modelAndView allows us to attach "Edit #[currentUser]" to the title (e.g. "Edit #Kyan7 - FilmPanda").
     * @return view of the current user's Edit Profile page if passwords don't match, otherwise view of the current user's Profile page.
     * @see com.filmpanda.filmpanda.service.UserService#editUserProfile(UserServiceModel, String)
     * @see BaseController#view(String, ModelAndView)
     */
    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute(name = "model") UserEditBindingModel model, ModelAndView modelAndView) {
        try {
            if (!model.getPassword().equals(model.getConfirmPassword())) {
                modelAndView.addObject("pageTitle", "Edit #" + model.getUsername());
                return view("edit-profile", modelAndView);
            }
            this.userService.editUserProfile(this.modelMapper.map(model, UserServiceModel.class), model.getOldPassword());
            return redirect("/users/profile");
        } catch (Exception e) {
            return redirect("/users/profile");
        }
    }

    /**
     * Lists all users in the system with their data (excluding passwords). Loads a view of the list. If the current user is the root user,
     * they may grant or remove admin rights directly from the page.
     * @param principal is used to locate the current user's username.
     * @param modelAndView allows us to attach multiple objects to the resulting web page:
     *                     -"User List" to the page title (e.g. "User List - FilmPanda")
     *                     -the list of users
     *                     -the current user's id, username and authorities
     * @return view of the list of users
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allUsers(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("pageTitle", "All Users");
        List<UserAdminListViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAdminListViewModel user = this.modelMapper.map(u, UserAdminListViewModel.class);
                    user.setAuthorities(u.getAuthorities()
                            .stream()
                            .map(a -> a.getAuthority())
                            .collect(Collectors.toSet()));
                    return user;
                })
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);

        modelAndView.addObject("currentUser", findCurrentUser(principal));
        return view("all-users", modelAndView);
    }

    /**
     * Allows removal of admin rights. (Only for root user)
     * @param id is the id of the user who is having his authorities changed.
     * @return view of the list of users
     */
    @PostMapping("/set-user/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView setUser(@PathVariable String id) {
        try {
            this.userService.setUserRole(id, "user");
            return redirect("/users/all");
        } catch (Exception e) {
            return redirect("/users/all");
        }

    }

    /**
     * Allows granting of admin rights. (Only for root user)
     * @param id is the id of the user who is having his authorities changed.
     * @return view of the list of users
     */
    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView setAdmin(@PathVariable String id) {
        try {
            this.userService.setUserRole(id, "admin");
            return redirect("/users/all");
        } catch (Exception e) {
            return redirect("/users/all");
        }
    }

}
