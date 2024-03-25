import {FC} from 'react';
import {createBrowserRouter, RouterProvider as Provider} from "react-router-dom";

import RootLayout from "@app/layouts/root";
import {routes} from "@pages/router";
import BasicErrorPage from "@pages/error/basic";

const router = createBrowserRouter([
    {
        path: "/",
        element: <RootLayout/>,
        children: [
            {
                errorElement: <BasicErrorPage/>,
                children: routes
            }
        ],
        errorElement: <BasicErrorPage/>,
    },
]);

const RouterProviders: FC = () => {
    return (<Provider router={router}/>);
};

export default RouterProviders;