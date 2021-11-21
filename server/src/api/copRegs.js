import { Router } from "express";
import validateHandlers from "../validate/validateHandler";
import { copRegListQueryHandler, copRegQueryHandler } from "./copRegsHandler";
import { tokenVerifyHandler } from "./authHandler";

const copRegsRouter = Router();

// GET /copRegs
copRegsRouter.get("/", [validateHandlers.CopRegListQuery, tokenVerifyHandler, copRegListQueryHandler]);
// GET /copRegs/:copRegId
copRegsRouter.get("/:copRegId", [validateHandlers.CopRegQuery, tokenVerifyHandler, copRegQueryHandler]);

export default copRegsRouter;
