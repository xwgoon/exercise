import React, { useState, useEffect } from "react";
import LazyLoad from "react-lazy-load";
import { makeStyles } from "@material-ui/core/styles";
import { get } from "lodash";
import copRegServerRestProvider from "../../dataprovider/ra-data-copRegServer-rest";
import { useRedirect } from "ra-core";

const useStyles = makeStyles({
    img: {
        objectFit: "cover",
        width: "100%",
        height: "100%",
    },
});

const ImageField = (props) => {
    const [image, setImage] = useState("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAKCAYAAACALL/6AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAVSURBVChTY/hPIhjVQAwYdBr+/wcAsVHeMBupnbkAAAAASUVORK5CYII=");
    const redirectTo = useRedirect();
    const classes = useStyles();

    const {
        record, source, width, height,
    } = props;

    useEffect(() => {
        const url = get(record, source);
        if (/^https?/.test(url)) {
            setImage(url);
        } else {
            copRegServerRestProvider("DOWNLOAD", "images", { url, width, height })
                .then(async (res) => {
                    const imageBlob = await res.blob();
                    setImage(URL.createObjectURL(imageBlob));
                    return 0;
                }).catch((e) => {
                    const { status } = e;
                    if (status === 401 || status === 403) {
                        // eslint-disable-next-line no-undef
                        localStorage.removeItem("token");
                        redirectTo("/login");
                    }
                });
        }
    }, [props]);

    return (
        <LazyLoad
            width={width}
            height={height}
            offset={200}
        >
            <img
                src={image}
                loading="lazy"
                title={props?.record?.image?.name}
                className={classes.img}
            />
        </LazyLoad>
    );
}

export default ImageField;
