/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/wrap-nodes
 */
import type { HTMLTagNames } from '../../types';
declare module '../../config' {
    interface Config {
        wrapNodes: {
            exclude: HTMLTagNames[];
            emptyBlockAfterInit: boolean;
        };
    }
}
