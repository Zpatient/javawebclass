/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:plugins/focus/README.md]]
 * @packageDocumentation
 * @module plugins/focus
 */
import type { IJodit } from '../../types';
declare module '../../config' {
    interface Config {
        autofocus: boolean;
        cursorAfterAutofocus: 'start' | 'end';
        saveSelectionOnBlur: boolean;
    }
}
export declare function focus(editor: IJodit): void;
