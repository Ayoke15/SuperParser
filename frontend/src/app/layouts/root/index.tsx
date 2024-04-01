import {FC} from 'react';
import {Box} from "@mui/joy";
import {Outlet} from "react-router-dom";

import HeaderUI from "@shared/ui/header";

const RootLayout: FC = () => {
    return (
        <>
            <HeaderUI/>
            <Box sx={{p: 2}}>
                <Outlet/>
            </Box>
        </>
    );
};

export default RootLayout;