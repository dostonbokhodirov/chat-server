package uz.doston.chatserver.controller.base;

import lombok.RequiredArgsConstructor;
import uz.doston.chatserver.service.base.BaseService;

@RequiredArgsConstructor
public class AbstractController<S extends BaseService> implements BaseController {

    protected final S service;

}
