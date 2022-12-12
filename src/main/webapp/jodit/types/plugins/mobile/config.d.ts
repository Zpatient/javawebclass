/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
declare module '../../config' {
    interface Config {
        /**
         * Mobile timeout for CLICK emulation
         */
        mobileTapTimeout: number;
        /**
         * After resize it will change buttons set for different sizes
         */
        toolbarAdaptive: boolean;
    }
}
export {};
