/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/dtd
 */
import type { IDictionary } from '../../types';
declare module '../../config' {
    interface Config {
        dtd: {
            /**
             * Remove extra br element inside block element after pasting
             */
            removeExtraBr: boolean;
            /**
             * Check when inserting a block element if it can be inside another block element (according `blockLimits`)
             */
            checkBlockNesting: boolean;
            /**
             * List of elements that contain other blocks
             */
            blockLimits: IDictionary<1>;
        };
    }
}