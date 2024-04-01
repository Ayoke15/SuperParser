import {createElement} from "react";
import {RouteObject} from "react-router-dom";

import HomePage from "@pages/home";
import NotFound from "@pages/error/not-found";
import ConfigPage from "@pages/config";
import EditConfigPage from "@pages/config/edit";


export const routes: RouteObject[] = [
    {
        index: true,
        element: createElement(HomePage),
    },
    {
        path: "config",
        element: createElement(ConfigPage),
    },
    {
        path: "config/edit/:id",
        element: createElement(EditConfigPage)
    },
    {
        path: "*",
        element: createElement(NotFound),
    },
]
