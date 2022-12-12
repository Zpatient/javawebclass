/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
declare module '../../config' {
    interface Config {
        /**
         * Decorate media elements
         */
        mediaInFakeBlock: boolean;
        /**
         * Decorate media element with tag
         */
        mediaFakeTag: string;
        /**
         * Media tags
         */
        mediaBlocks: string[];
    }
}
export {};