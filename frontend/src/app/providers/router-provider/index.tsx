import {FC} from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import NotFoundPage from "@pages/not-found";
import {NamePages, Page, routes} from "@pages/router";

const RouterProvider: FC = () => {
    const pages: Page[] = Object.keys(NamePages)
        .filter(key => !isNaN(Number(key)))
        .map(key => routes[Number(key) as NamePages])
    return (
        <BrowserRouter>
            <Routes>
                {pages.map(page => (
                    <Route
                        key={page.path}
                        path={page.path}
                        element={page.component}
                    />
                ))}
                <Route path={"*"} element={<NotFoundPage/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default RouterProvider;