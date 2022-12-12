/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module request
 */
import type { AjaxOptions } from '../../types';
declare module '../../config' {
    interface Config {
        /**
         * A set of key/value pairs that configure the Ajax request. All settings are optional
         */
        defaultAjaxOptions: AjaxOptions;
    }
}
