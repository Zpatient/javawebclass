/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:plugins/tooltip/README.md]]
 * @packageDocumentation
 * @module plugins/tooltip
 */

import type { IViewBased } from '../../../../types';
import { UIElement } from '../../element';
export declare class UITooltip extends UIElement {
    private __isOpened;
    className(): string;
    protected constructor(view: IViewBased);
    private __useCount;
    /**
     * Creates only one instance of the tooltip for the container
     */
    static make(view: IViewBased): UITooltip;
    private __delayShowTimeout;
    private __delayOpen;
    private __open;
    private __setPosition;
    private __close;
    destruct(): void;
}