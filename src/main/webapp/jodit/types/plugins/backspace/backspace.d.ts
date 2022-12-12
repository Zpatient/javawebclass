/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:plugins/backspace/README.md]]
 * @packageDocumentation
 * @module plugins/backspace
 */
import type { IJodit } from '../../types';
import { Plugin } from '../../core/plugin';
import './config';
export declare class backspace extends Plugin {
    requires: string[];
    protected afterInit(jodit: IJodit): void;
    protected beforeDestruct(jodit: IJodit): void;
    /**
     * After Delete command remove extra BR
     */
    private afterDeleteCommand;
    /**
     * Listener BackSpace or Delete button
     */
    private onDelete;
    /**
     * Remove node and replace cursor position out of it
     */
    private safeRemoveEmptyNode;
}