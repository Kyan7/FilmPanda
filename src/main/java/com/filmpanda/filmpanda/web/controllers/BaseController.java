package com.filmpanda.filmpanda.web.controllers;

import com.filmpanda.filmpanda.domain.models.service.UserServiceModel;
import com.filmpanda.filmpanda.domain.models.view.UserAuthoritiesViewModel;
import com.filmpanda.filmpanda.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.stream.Collectors;

/**
 * Generalizes common methods among all controllers.
 */
public abstract class BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    protected BaseController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Helps load web pages which require server information (e.g. article ids, movie titles, etc.).
     * @param view shows which page to visualize.
     * @param modelAndView is used for transfering data to the pages.
     * @return a view of the page.
     */
    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);
        return modelAndView;
    }

    /**
     * Helps load web pages which do not require server information (e.g. index).
     * @param view shows which page to visualize.
     * @return a view of the page.
     */
    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }

    /**
     * Redirects us to a url.
     * @param url is the url to which we are redirected.
     * @return a view of the page.
     */
    protected ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }

    /**
     * Finds the current user (with their authorities) through their name.
     * @param principal is used to find the current user's name.
     * @return the current user (with their authorities)
     */
    protected UserAuthoritiesViewModel findCurrentUser(Principal principal) {
        UserServiceModel userServiceModel = this.userService.findUserByUsername(principal.getName());
        UserAuthoritiesViewModel currentUser = this.modelMapper.map(userServiceModel, UserAuthoritiesViewModel.class);
        currentUser.setAuthorities(userServiceModel.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()));
        return currentUser;
    }
}

