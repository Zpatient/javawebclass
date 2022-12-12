/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:modules/messages/README.md]]
 * @packageDocumentation
 * @module modules/messages
 */

import type { IMessages, IViewBased, MessageVariant } from '../../types';
import { UIGroup } from '../../core/ui/group/group';
/**
 * Plugin display pop-up messages in the lower right corner of the editor
 */
export declare class UIMessages extends UIGroup implements IMessages {
    readonly options: {
        defaultTimeout: number;
        defaultOffset: number;
    };
    className(): string;
    constructor(jodit: IViewBased, options?: {
        defaultTimeout: number;
        defaultOffset: number;
    });
    info(text: string, timeout?: number): void;
    success(text: string, timeout?: number): void;
    error(text: string, timeout?: number): void;
    message(text: string, variant?: MessageVariant, timeout?: number): void;
    /**
     * Show popup error in the bottom of editor
     *
     * @param text - Text message
     * @param variant - Additional class for status. Allow: info, error, success
     * @param timeout - How many seconds show error
     * options.showMessageErrorTime = 2000
     * @example
     * ```javascript
     * const editor = Jodit.make('#editors');
     * editor.e.fire('errorMessage', 'Error 123. File has not been upload');
     * editor.e.fire('errorMessage', 'You can upload file', 'info', 4000);
     * editor.e.fire('errorMessage', 'File was uploaded', 'success', 4000);
     * ```
     */
    private __message;
    private __getRemoveCallback;
    private __messages;
    private __calcOffsets;
}