import { getCopRegList, getCopReg } from "../copRegs/copRegs";
import serverInnerLogger from "../log/serverInnerLog";

export const copRegQueryHandler = (req, res) => {
    const { copRegId } = req.params;
    getCopReg(copRegId).then((copReg) => {
        if (copReg) {
            serverInnerLogger.info(`Successfully query copReg for id: ${copRegId}, send the result`);
            res.status(200)
                .set({ "Cache-Control": "public" })
                .json({ copReg })
                .end();
        } else {
            serverInnerLogger.error(`Unsuccessfully query copReg for id: ${copRegId}, send the result`);
            res.status(404)
                .json({ message: `Not found copReg for id: ${copRegId}` })
                .end();
        }
        return 0;
    }).catch((err) => {
        serverInnerLogger.error(`Unsuccessfully query copReg for id: ${copRegId}, with error: ${err}, send the result`);
        res.status(500)
            .json({ message: err })
            .end();
    });
};

export const copRegListQueryHandler = (req, res) => {
    const filter = req.query;
    const { sortBy, perPage, page } = req.query;
    getCopRegList({
        filter,
        orderBy: sortBy,
        pagination: { perPage, page },
    }).then((result) => {
        serverInnerLogger.info("Successfully query copRegList, send the result");
        res.status(200)
            .json({ ...result })
            .end();
        return 0;
    }).catch((err) => {
        serverInnerLogger.error(`Unsuccessfully query copRegList, with error: ${err}`);
        res.status(500)
            .json({ message: err })
            .end();
    });
};
