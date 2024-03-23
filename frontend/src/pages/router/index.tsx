import {ReactNode} from "react";
import HomePage from "@pages/home";

export enum NamePages {
    HOME,
}

export interface Page {
    name: string,
    path: string,
    component: ReactNode,
    show: boolean,
}

export const routes: { [key in NamePages]: Page } = {
    [NamePages.HOME]: {
        name: "Главная",
        path: "/",
        component: <HomePage/>,
        show: true,
    } as Page,
}

