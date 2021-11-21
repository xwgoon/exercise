import { Router } from "express";
import uploadHandler from "./uploadHandler";
import storeHandler from "./storeHandler";
import downloadHandler from "./downloadHandler";
import validateHandlers from "../validate/validateHandler";
import { tokenVerifyHandler } from "./authHandler";

const imagesRouter = Router();

// GET /images/:imageId/download
imagesRouter.get("/:imageId/download", [validateHandlers.ImageDownload, tokenVerifyHandler, downloadHandler]);
// POST /images
imagesRouter.post("/", [uploadHandler, validateHandlers.ImageUpload, tokenVerifyHandler, storeHandler]);

export default imagesRouter;
