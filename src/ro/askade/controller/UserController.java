package ro.askade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.askade.exception.*;
import ro.askade.service.UserService;
import ro.askade.smartRecycle.model.User;
import ro.askade.utils.JsonResponse;
import java.math.BigInteger;

/**
 * Created by AdrianIonita on 3/8/2017.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/userController/recycleUserList", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getUserList(){
        return JsonResponse.forSuccess(userService.listUsers());
    }

    /**
     * @param userCode
     * @return
     */
    @RequestMapping(value = "/getRecycleUser", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getRecycleUser(@RequestParam(value = "userCode", required = true) String userCode){
        return JsonResponse.forSuccess(userService.getUserByCode(userCode));
    }

    /**
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getRecycleUserById", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getRecycleUserById(@RequestParam(value = "userId", required = true)BigInteger userId){
        return JsonResponse.forSuccess(userService.getUserById(userId));
    }

    /**
     * @param currentUser
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateRecycleUser", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateRecycleUser(@RequestHeader("currentUser") String currentUser, @RequestBody User user){
        if (user.getUserId() == null){
            return JsonResponse.forError("User id was not determined");
        }else{
            try {
                userService.updateUser(currentUser,user);
            } catch (AskadeNoUserDefinedException e) {
                return JsonResponse.forError(e.getMessage());
            } catch (AskadeUserNotAvailableYetEx e) {
                return JsonResponse.forError(e.getMessage());
            } catch (AskadeUserExpiredException e) {
                return JsonResponse.forError(e.getMessage());
            } catch (AskadeConcurentUpdateException e) {
                return JsonResponse.forError(e.getMessage());
            }
        }
        return JsonResponse.forSuccess();
    }

    /**
     * @param currentUser
     * @param user
     * @return
     */
    @RequestMapping(value = "/setAskadeUser", method = RequestMethod.POST)
    public @ResponseBody JsonResponse createAskadeUser(@RequestHeader ("currentUser") String currentUser,@RequestBody User user){
        try {
            userService.addUser(currentUser, user);
        } catch (AskadeNoUserDefinedException e) {
            return JsonResponse.forError(e.getMessage());
        } catch (AskadeUserExpiredException e) {
            return JsonResponse.forError(e.getMessage());
        } catch (AskadeUserNotAvailableYetEx e) {
            return JsonResponse.forError(e.getMessage());
        }
        return JsonResponse.forSuccess();
    }

    /**
     * @param recyclingUser
     * @return
     */
    //@CrossOrigin(origins = "http://localhost:9080", maxAge = 3600)
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public @ResponseBody JsonResponse loginUser(@RequestBody User recyclingUser){
        User user = null;
        try {
            user = userService.loginUser(recyclingUser);
        } catch (AskadeUserNotAvailableYetEx askadeUserNotAvailableYetEx) {
            return JsonResponse.forError(askadeUserNotAvailableYetEx.getMessage());
        } catch (AskadeUserExpiredException e) {
            return JsonResponse.forError(e.getMessage());
        } catch (AskadeIncorrectPasswordException e) {
            return JsonResponse.forError(e.getMessage());
        }
        if(user != null){
            return JsonResponse.forSuccess(user);
        }else{
            return JsonResponse.forError("User does not exists");
        }
    }
}
