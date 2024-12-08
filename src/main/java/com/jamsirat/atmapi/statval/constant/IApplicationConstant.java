package com.jamsirat.atmapi.statval.constant;

public interface IApplicationConstant {

    interface ContextPath{
        String ORDER = "/v1/order-service";
        String USER = "/v1/user-service";
        String AUTHENTICATION = "/api/v1/auth";
        String ACCESS = "/api/v1/access";
        String USER_PROFILE = "/api/v1/user";
        String MENU = "/api/v1/menu";
    }

    interface Path{

        interface Cart {
            String ADD_CART = "/cart/add-cart";
            String GET_CART = "/cart/get-cart";
        }

        interface User {
            String COMPLETE_PROFILE ="/complete-profile";
            String UPDATE_PROFILE ="/update-profile";
            String GET_DETAIL_PROFILE = "/detail-profile";
            String FETCH_ALL_USERS = "/fetch-users/{page}/{size}";

        }

        interface Authorization {
            String GRANT_ACCESS ="/grant-access";
        }

        interface Authentication {
            String REGISTER = "/register";
            String LOGIN = "/login";
            String REFRESH_TOKEN = "/refresh-token";
            String VERIFY_ACCOUNT = "/verifyEmail";
        }

        interface Menu {
            String FETCH_MENU = "/fetch-menu";
        }

    }

    interface  DefaultNumber {
        Long OUT_OF_STOCK = 0L;

    }

    interface StaticDefaultMessage {

        interface ExceptionMessage {
            String EMAIL_VALID_FORMAT = "Email does not valid";
            String EMAIL_NOT_NULL = "Email may not be null";
            String FIRST_NAME_NOT_NULL = "First name may not be null";
            String DATA_NOT_FOUND_EXCEPTION = "Data is not found";
            String USER_NOT_FOUND_EXCEPTION = "User does not exists";
            String USERNAME_NOT_FOUND = "Username is not found";
            String USER_NOT_ACTIVATED_EXCEPTION = "User is not activated";
            String EMAIL_ALREADY_TAKEN = "Email is already registered";
            String ROLE_NOT_FOUND = "Role not found!";
            String FAILED_DATA_USER = "Failed to get data user!";
            String OUT_OF_STOCK_EXCEPTION = "Sorry, we are out of stock";
            String PRODUCT_NOT_FOUND_EXCEPTION = "Product with id is not exists";
            String TOKEN_IS_INVALID = "Token is invalid or expired";
            String ACCESS_DENIED = "Access denied for this role";
            String BAD_CREDENTIALS = "Password or email incorrect";
            String AUTHORIZATION_HEADER_INVALID = "Authorization header and Bearer is not set";
        }

        interface DeveloperExceptionMessage {
            String DATA_NOT_FOUND_EXCEPTION = "Please check your request data";
            String USER_NOT_ACTIVATED_EXCEPTION = "Please activate your account";
            String EMAIL_ALREADY_TAKEN = "Please choose another email";
            String ROLE_NOT_FOUND = "Please check your database";
            String PRODUCT_NOT_FOUND_EXCEPTION = "Make sure product id is correct";
            String OUT_OF_STOCK_EXCEPTION = "Please contact your Administrator";
            String TOKEN_IS_INVALID       = "Please do login";
            String ACCESS_DENIED = "Access denied for this role";
            String AUTHORIZATION_HEADER_INVALID = "Please check your header";
            String BAD_CREDENTIALS = "Please check your password and email";
        }

        interface SuccessMessage {
            String TRANSACTION_SUCCESS = "Transaction created successfully";
            String DATA_ADDED_SUCCESSFULLY ="Data added successfully";
            String DATA_DELETED_SUCCESSFULLY = "Data deleted successfully";
            String DATA_UPDATED_SUCCESSFULLY = "Data updated successfully";
            String DATA_FETCH_SUCCESSFULLY = "Data fetched successfully";
            String NO_CONTENT = "Data is empty";
            String TRANSACTION_HISTORY = "Transaction History fetch successfully";
            String LOGOUT_MSG_SUCCESS = "Logout successful";
            String VERIFY_ACCOUNT  = " Please Verify your account";
            String LOGIN_SUCCESSFUL = "Login account successfully";
            String REGISTRATION_SUCCESSFUL = "Please login to continue";
        }

        interface DeveloperSuccessMessage {
            String DATA_ADDED_SUCCESSFULLY ="Data Saved";
            String DATA_DELETED_SUCCESSFULLY = "Data deleted";
            String DATA_UPDATED_SUCCESSFULLY = "Data updated";
            String DATA_FETCH_SUCCESSFULLY = "Data fetched ";
            String TRANSACTION_SUCCESS = "Please do payment";
            String TRANSACTION_HISTORY = "Transaction histories";
            String LOGOUT_MSG_SUCCESS = "Please do login to continue";
            String VERIFY_ACCOUNT = "Verification link was sent to your email";
            String LOGIN_SUCCESSFUL = "Profile we trust!";
            String REGISTRATION_SUCCESSFUL = "Registration Successful";
        }

    }


}