import { Router } from "express";
import validateHandlers from "../validate/validateHandler";
import { loginHandler, registerHandler } from "./authHandler";

const authRouter = Router();

// POST /auth/login
authRouter.post("/login", [validateHandlers.authLogin, loginHandler]);
// POST /auth/register
authRouter.post("/register", [validateHandlers.authRegister, registerHandler]);

export default authRouter;
