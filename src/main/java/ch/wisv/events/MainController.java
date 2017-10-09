package ch.wisv.events;

import ch.wisv.events.utils.AppLocation;
import org.mitre.openid.connect.model.OIDCAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Copyright (c) 2016  W.I.S.V. 'Christiaan Huygens'
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
@Controller
@PreAuthorize("isAuthenticated()")
public class MainController {

    /**
     * Method index.
     *
     * @param model of type Model
     * @param auth  of type OIDCAuthenticationToken
     * @return String
     */
    @GetMapping("/")
    public String index(Model model, OIDCAuthenticationToken auth) {
        model.addAttribute("locations", AppLocation.getLocations(auth.getAuthorities()));

        return "index";
    }

//    /**
//     * Default error page.
//     *
//     * @return String
//     */
//    @GetMapping("/error")
//    public String error() {
//        return "error";
//    }
}
