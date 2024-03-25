import {createElement} from "react";
import {RouteObject} from "react-router-dom";

import HomePage from "@pages/home";
import NotFound from "@pages/error/not-found";


export const routes: RouteObject[] = [
    {
        index: true,
        element: createElement(HomePage),
    },
    {
        path: "*",
        element: createElement(NotFound),
    },
]
