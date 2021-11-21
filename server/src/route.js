import imagesRouter from "./api/images";
import copRegsRouter from "./api/copRegs";
import authRouter from "./api/auth";

const setRoute = (app) => {
    app.use("/images", imagesRouter)
        .use("/copRegs", copRegsRouter)
        .use("/auth", authRouter);
};

export default setRoute;
